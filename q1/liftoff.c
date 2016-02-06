/* THIS IMPLEMENTATION DOES NOT SEEM CORRECT */
/* FIXME FIXME FIXME */
#include <stdio.h>
int nums[] = { 1, 1, 1, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8, 8, 10, 10, 11, 12, 16 };
#define COUNT (sizeof(nums) / sizeof(int))
int main(int argc, char** argv) {	
  printf("Count is: %lu\n", COUNT);
	int search = 2;
	int index = COUNT / 2;
	int move_sz = COUNT / 4;
	int curr = nums[index];
	while(curr != search) {
		if(!move_sz)
			move_sz = 1;
		if(curr > search)
			index -= move_sz;
		else
			index += move_sz;
		move_sz >>= 2;
		curr = nums[index];
	}

	printf("Number is at index %d.\n", curr);
	return 0;
}
