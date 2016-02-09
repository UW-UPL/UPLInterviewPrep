#include <stdio.h>

int firstOccurrence(int *nums, int length, int k) {
    int first = 0;
    int last = length - 1;
    while (first < last) {
        int med = first + (last - first) / 2;
        if (nums[med] < k)
            first = med + 1;
        else if (nums[med] > k)
            last = med - 1;
        else
            last = med;
    }
    if (nums[first] == k) return first;
    else return -1;
}

int main(int argc, char** argv) {
    int nums[] = { 1, 1, 1, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8, 8, 10, 10, 11, 12, 16 };
    int length = sizeof(nums) / sizeof(int);
    int i, k;
    printf("%s", "Array [");
    for (i = 0; i < length; i++) {
        printf("%d%s", nums[i], i != length - 1 ? ", " : "]\n");
    }
    for (k = 0; k < 20; k++) {
        printf("%d found at %d\n", k, firstOccurrence(nums, length, k));
    }
    return 0;
}
