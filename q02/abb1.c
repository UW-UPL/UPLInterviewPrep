#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static int __log10(int num) {
	int res = 0;
	for(;num > 0;res++)
		num /= 10;
	return res;
}
static int isAbbr(char* name, char* abbr) {
	int name_len = strlen(name);
	int abbr_len = strlen(abbr);
	int name_pos = 0;
	int abbr_pos = 0;
	while(name_pos < name_len && abbr_pos < abbr_len &&
		abbr[abbr_pos] != 0 && name[name_pos] != 0)
	{
		if(abbr[abbr_pos] >= '0' && abbr[abbr_pos] <= '9')
		{
			int abbr_num = atoi(abbr + abbr_pos);
			abbr_pos += __log10(abbr_num);
			name_pos += abbr_num;
		} else {
			if(abbr[abbr_pos] != name[name_pos])
				return 0;
			name_pos++;
			abbr_pos++;
		}
	}
	if (name_pos > name_len) return 0;
	if(abbr[abbr_pos] == name[name_pos] && abbr[abbr_pos] == 0)
		return 1;
	return 0;
}
int main(int argc, char** argv) {
	printf("1: %s\n", isAbbr("LOCALIZATION", "L10N") ? "TRUE" : "FALSE");
	printf("2: %s\n", isAbbr("LOCALIZATION", "L9N") ? "TRUE" : "FALSE");
	printf("3: %s\n", isAbbr("LOCALIZATION", "L10Q") ? "TRUE" : "FALSE");
	return 0;
}
