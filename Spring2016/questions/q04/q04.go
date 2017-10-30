package q04

// Run the code online here: https://play.golang.org/.
// Or, run `go test` (see q04_test.go).

// Node contains a child nodes and data.
type Node struct {
	Left  *Node
	Right *Node
	Value rune
}

func (node *Node) getGrandchildren() []*Node {
	// Since we have a binary tree, we should have at most 4 grandchildren.
	ret := make([]*Node, 4)

	if node.Left != nil {
		if node.Left.Left != nil {
			ret = append(ret, node.Left.Left)
		}

		if node.Left.Right != nil {
			ret = append(ret, node.Left.Right)
		}
	}

	if node.Right != nil {
		if node.Right.Left != nil {
			ret = append(ret, node.Right.Left)
		}

		if node.Right.Right != nil {
			ret = append(ret, node.Right.Right)
		}
	}

	return ret
}

func (node *Node) anyGrandchildrenEqual() bool {
	grandchildren := node.getGrandchildren()

	dict := make(map[rune]bool)

	for _, gchild := range grandchildren {
		if gchild == nil {
			continue
		}

		key := gchild.Value

		if _, hasKey := dict[key]; hasKey {
			return true
		}

		dict[key] = true
	}

	if node.Left != nil {
		leftEq := node.Left.anyGrandchildrenEqual()
		if leftEq {
			return true
		}
	}

	if node.Right != nil {
		rightEq := node.Right.anyGrandchildrenEqual()
		if rightEq {
			return true
		}
	}

	return false
}

// Tree is a tree containing Nodes, all descending from a root Node.
type Tree struct {
	Root Node
}

// AnyGrandchildrenEqual returns true iff any two grandchildren are equal at
// any equal level in the tree.
func (tree *Tree) AnyGrandchildrenEqual() bool {
	return tree.Root.anyGrandchildrenEqual()
}
