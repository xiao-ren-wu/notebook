void charName(char data[],char now[]){
	if ('\0'==now[0]){
		printf("%s \n", data);
	}
	else{
		for (int i = 0; '\0'!=now[i]; i++){
			int temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			charName(data, now+1);
			temp = data[0];
			data[0] = data[i];
			data[i] = temp;
		}
	}
}