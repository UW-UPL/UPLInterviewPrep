module Main where

import Control.Monad
import Data.List
import qualified Data.Set as S
import Test.QuickCheck

data Tree a = Leaf | Node (Tree a) a (Tree a) deriving (Eq, Show)

-- This incantation generates random binary trees. It is necessary
-- for the QuickCheck testing we will be doing

genTree :: Arbitrary a => Gen (Tree a)
genTree = frequency [ (1, return Leaf)
                    , (2, liftM3 Node arbitrary arbitrary arbitrary)
                    ]

instance Arbitrary a => Arbitrary (Tree a) where
  arbitrary = genTree

val :: Tree a -> Maybe a
val Leaf = Nothing
val (Node _ a _) = Just a

children :: Tree a -> [a]
children (Node (Node _ l _) _ Leaf) = [l]
children (Node Leaf _ (Node _ r _)) = [r]
children (Node (Node _ l _) _ (Node _ r _)) = [l, r]
children _ = []

grandChildren :: Tree a -> [a]
grandChildren Leaf = []
grandChildren (Node l _ r) = (children l) ++ (children r)

anyEqual :: Ord a => [a] -> Bool
anyEqual l = S.size (S.fromList l) /= length l

anyGrandchildrenEqual :: Ord a => Tree a -> Bool
anyGrandchildrenEqual Leaf = False
anyGrandchildrenEqual t@(Node l _ r) = (anyEqual (grandChildren t)) || anyGrandchildrenEqual l || anyGrandchildrenEqual r

-- properties of the functions above that should be true for all inputs

prop_upToTwoChildren :: Tree a -> Bool
prop_upToTwoChildren t = length (children t) <= 2

prop_upToFourGrandchildren :: Tree a -> Bool
prop_upToFourGrandchildren t = length (grandChildren t) <= 4

prop_childrensChildrenAreGrandchildren :: Eq a => Tree a -> Tree a -> Bool
prop_childrensChildrenAreGrandchildren t t' =
  all id $ map (\n -> elem n gc) (children t ++ children t')
  where gc = grandChildren (Node t undefined t')

prop_noneEqualInUniq :: Ord a => [a] -> Bool
prop_noneEqualInUniq l = not $ anyEqual (S.toList (S.fromList l))

prop_selfConcatEqual :: Ord a => [a] -> Bool
prop_selfConcatEqual [] = True
prop_selfConcatEqual l = anyEqual (l ++ l)

prop_allEqualAnyOrder :: Ord a => [a] -> Bool
prop_allEqualAnyOrder l = all (\l' -> anyEqual l' == ans) (permutations front)
  where front = take 5 l
        ans = anyEqual front

-- the main function that tests all of the properties on 100 random inputs

type PropStringTree = Tree String -> Bool
type PropStringList = [String] -> Bool

main :: IO ()
main = do
  quickCheck (prop_upToTwoChildren :: PropStringTree)
  quickCheck (prop_upToFourGrandchildren :: PropStringTree)
  quickCheck (prop_childrensChildrenAreGrandchildren :: Tree String -> Tree String -> Bool)
  quickCheck (prop_noneEqualInUniq :: PropStringList)
  quickCheck (prop_selfConcatEqual :: PropStringList)
  quickCheck (prop_allEqualAnyOrder :: PropStringList)
