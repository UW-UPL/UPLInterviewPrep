package main

// Run the code online here: https://play.golang.org/

import "fmt"

type Node struct {
	left  *Node
	right *Node
	value rune
}

func (node *Node) getGrandchildren() []*Node {
	// since we have a binary tree, we should have at most 4 grandchildren
	ret := make([]*Node, 4)

	if node.left != nil {
		if node.left.left != nil {
			ret = append(ret, node.left.left)
		}

		if node.left.right != nil {
			ret = append(ret, node.left.right)
		}
	}

	if node.right != nil {
		if node.right.left != nil {
			ret = append(ret, node.right.left)
		}

		if node.right.right != nil {
			ret = append(ret, node.right.right)
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

		key := gchild.value
		_,
			hasKey := dict[key]
		if hasKey {
			return true
		} else {
			dict[key] = true
		}
	}

	if node.left != nil {
		leftEq := node.left.anyGrandchildrenEqual()
		if leftEq {
			return true
		}
	}

	if node.right != nil {
		rightEq := node.right.anyGrandchildrenEqual()
		if rightEq {
			return true
		}
	}

	return false

}

type Tree struct {
	root Node
}

func (tree *Tree) anyGrandchildrenEqual() bool {
	return tree.root.anyGrandchildrenEqual()
}

func main() {
	tree1 := Tree{
		root: Node{
			value: 'a',
			left: &Node{
				value: 'b',
				left: &Node{
					value: 'c',
					right: &Node{
						value: 'e',
					},
				},
				right: &Node{
					value: 'd',
				},
			},
			right: &Node{
				value: 'f',
				right: &Node{
					value: 'g',
					left: &Node{
						value: 'h',
					},
				},
			},
		},
	}

	tree2 := Tree{
		root: Node{
			value: 'a',
			left: &Node{
				value: 'b',
				left: &Node{
					value: 'c',
					left: &Node{
						value: 'd', // match
					},
				},
				right: &Node{
					value: 'h',
					right: &Node{
						value: 'd', // match
					},
				},
			},
			right: &Node{
				value: 'a',
				left: &Node{
					value: 'f',
					left: &Node{
						value: 'g',
					},
					right: &Node{
						value: 'd',
					},
				},
			},
		},
	}

	testsOK := true
	testTree1 := tree1.anyGrandchildrenEqual()

	if testTree1 != false {
		fmt.Println("TEST 1 FAILED!")
		testsOK = false
	}

	testTree2 := tree2.anyGrandchildrenEqual()

	if testTree2 != true {
		fmt.Println("TEST 2 FAILED")
		testsOK = false
	}

	fmt.Println("DONE")

	if testsOK {
		fmt.Println("> SUCCESS!")
	} else {
		fmt.Println("> FAILURE")
	}

	return
}
