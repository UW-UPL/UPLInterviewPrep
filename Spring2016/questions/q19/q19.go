package q19

import (
	"errors"
	"math"
)

// Test this via `go test`.

// PairyTime returns the pair of indices (x, y) such that x < y and num[x] +
// nums[y] is a multiple of k (i.e. there exists some natural m such that:
// nums[x]+nums[y] = m*k).
func PairyTime(k int, nums []int) (int, int, error) {
	pairIndexMap := make(map[int]int)
	for i, v := range nums {
		// Use `floorMod` instead of `%` to "properly handle" negatives.
		vmod := floorMod(v, k)
		if j, ok := pairIndexMap[k-vmod]; ok {
			// By order of iteration, j < i.
			return j, i, nil
		}

		pairIndexMap[vmod] = i

		// Special check for vmod == 0  since we mod everything by k, we will
		// never get an explicit value of vmod == k.
		if vmod == 0 {
			pairIndexMap[k] = i
		}
	}

	return -1, -1, errors.New("unsuccessful search")
}

func floorMod(a, k int) int {
	return a - (floorDiv(a, k) * k)
}
func floorDiv(x, y int) int {
	return int(math.Floor(float64(x) / float64(y)))
}
