#include <stdio.h>
#define COUNT 16
int nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
int main(int argc, char** argv) {	
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
