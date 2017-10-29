package q19

import (
	"fmt"
	"testing"
)

func TestPairyTime(t *testing.T) {
	for tci, tc := range []struct {
		k          int
		nums       []int
		wantError  bool
		wantResult [2]int
	}{{
		k:          3,
		nums:       []int{-2, 2},
		wantResult: [2]int{0, 1},
	}, {
		k:          8,
		nums:       []int{1, -2, -9, 1, 1, 1},
		wantResult: [2]int{0, 2},
	}, {
		k:          7,
		nums:       []int{0, 3, 1, 1, 0},
		wantResult: [2]int{0, 4},
	}, {
		k:         4,
		wantError: true,
	}, {
		k:         3,
		nums:      []int{1, 1},
		wantError: true,
	}} {
		t.Run(fmt.Sprintf("Test case #%d", tci+1), func(t *testing.T) {
			i, j, err := PairyTime(tc.k, tc.nums)
			if err != nil {
				if tc.wantError {
					return
				}

				t.Fatalf("PairyTime(%d, %v) unexpectedly failed: %v", tc.k, tc.nums, err)
			}

			if err == nil && tc.wantError {
				t.Errorf("PairyTime(%d, %v) unexpectedly succeeded", tc.k, tc.nums)
			}

			if i != tc.wantResult[0] || j != tc.wantResult[1] {
				t.Errorf("PairyTime(%d, %v) = %d, %d; want %d, %d", tc.k, tc.nums, i, j, tc.wantResult[0], tc.wantResult[1])
			}
		})
	}
}
