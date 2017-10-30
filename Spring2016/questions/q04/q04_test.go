package q04

import "testing"

func TestTreeAnyGrandchildrenEqual(t *testing.T) {
	for _, tc := range []struct {
		name       string
		tree       Tree
		wantResult bool
	}{{
		name: "when nothing is equal in the tree",
		tree: Tree{
			Root: Node{
				Value: 'a',
				Left: &Node{
					Value: 'b',
					Left: &Node{
						Value: 'c',
						Right: &Node{
							Value: 'e',
						},
					},
					Right: &Node{
						Value: 'd',
					},
				},
				Right: &Node{
					Value: 'f',
					Right: &Node{
						Value: 'g',
						Left: &Node{
							Value: 'h',
						},
					},
				},
			},
		},
		wantResult: false,
	}, {
		name: "when only siblings are equal",
		tree: Tree{
			Root: Node{
				Value: 'a',
				Left:  &Node{Value: 'z'},
				Right: &Node{Value: 'z'},
			},
		},
		wantResult: false,
	}, {
		name: "when grandchildren are equal",
		tree: Tree{
			Root: Node{
				Value: 'a',
				Left: &Node{
					Value: 'b',
					Left: &Node{
						Value: 'c',
						Left: &Node{
							Value: 'd', // match
						},
					},
					Right: &Node{
						Value: 'h',
						Right: &Node{
							Value: 'd', // match
						},
					},
				},
				Right: &Node{
					Value: 'a',
					Left: &Node{
						Value: 'f',
						Left: &Node{
							Value: 'g',
						},
						Right: &Node{
							Value: 'd',
						},
					},
				},
			},
		},
		wantResult: true,
	}} {
		t.Run(tc.name, func(t *testing.T) {
			if tc.tree.AnyGrandchildrenEqual() != tc.wantResult {
				t.Errorf("AnyGrandchildren was not %v", tc.wantResult)
			}
		})
	}
}
