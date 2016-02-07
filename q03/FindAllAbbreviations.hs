-- An algorithm to find all possible abbreviations of a word
-- e.g. "the" -> ["the", "3", "t1e", "2e", "1h1", ...]
-- c + c, c + n, n + c, n + n
-- assumes argument string does not contain numerics

-- Note: this problem is similar to the power set problem

import Data.List (intercalate, sort)

isNumeric :: Char -> Bool
isNumeric c = c `elem` ['0'..'9']

appendAbbr :: Char -> String -> [String] -> [String]
-- takes a character, a string, and a list of previous results
appendAbbr c str acc =
  -- take the numeric prefix (if it exists)
  -- e.g. "123xyz4" => ("123", "xyz4")
  let (prefix, suffix) = span (isNumeric) str
      -- parse prefix into an integer
      num = read prefix :: Integer
      -- increment it and convert back to a string
      incrementedPrefix = show (1 + num)
      -- create new solution strings...
      additions  = if (null prefix)
                    -- [char + char, num + char]
                    then [c:str, '1':str]
                    -- char + num, num + num
                    else [c:str, incrementedPrefix ++ suffix]
  -- return the new solutions appended to the previous results
  in acc ++ additions

findAllAbbreviations :: String -> [String]
findAllAbbreviations [singleChar] = [[singleChar], ['1']]
findAllAbbreviations (c:cs) =
  -- take off the first char (`c`); the rest of the string is `cs`
  let subresults = findAllAbbreviations cs
  -- foldr is "reduce" in other languages
  in  foldr (appendAbbr c) [] subresults

main = do
  let abbrs   = findAllAbbreviations "wisconsin"
      -- get pretty output
      output  = intercalate "\n" $ sort abbrs
  putStrLn output
  return ()
