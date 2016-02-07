'''
The tree implementation is undefined. This is something you should ask about
when given a plain `tree` question. It could be a:
  - binary tree (Left and Right children)
  - binary search tree (Left and Right children w/ some ordering)
  - `list tree` (children stored in a list)
  - red/black tree (painful...)
  - or something else!
'''

class TreeNode:
  def __init__(self, data = None, *children):
    self.data = data
    self.children = children

  def is_leaf(self):
    return len(self.children) == 0

  # as required by the answer
  def grandchildren(self):
    if self.is_leaf():
      return []
    else:
      ret = []

      for child in self.children:
        for gchild in child.children:
          ret.append(gchild)

      return ret

  def any_grandchildren_equal(self):
    if self.is_leaf():
      # no grandchildren!
      return False
    else:
      data_list = map(lambda node: node.data, self.grandchildren())
      data_set = set(data_list)

      # there will be duplicates if the list size != set size
      # because a set removes duplicates
      diff_exists = len(data_list) != len(data_set)

      if diff_exists:
        return True
      else:
        return any(map(lambda child: child.any_grandchildren_equal(),
          self.children))

class Tree:
  def __init__(self, root):
    self.root = root

  def any_grandchildren_equal(self):
    return self.root.any_grandchildren_equal()

if __name__ == '__main__':
 tree1 = Tree(TreeNode('a', TreeNode('b', TreeNode('c'), TreeNode('d'),
 TreeNode('e')), TreeNode('f'), TreeNode('g', TreeNode('h'))))

 print('tree1: ' + str(tree1.any_grandchildren_equal()) + ' should be False')

 tree2 = Tree(TreeNode('a', TreeNode('b'), TreeNode('a'), TreeNode('c',
   TreeNode('d'), TreeNode('e')), TreeNode('f', TreeNode('d'),
     TreeNode('g'))))

 print('tree2: ' + str(tree2.any_grandchildren_equal()) + ' should be True')

