def string_list_editor(strings):
	start_chars = count_chars(strings)	
	arranged = []

	for string in strings:		#sort the input list to find median
		arranged.append(len(string))
	sorted(arranged)

	if len(arranged) % 2 == 0:			#calculate median
		median = (arranged[len(arranged)/2] + arranged[(len(arranged)/2) - 1]) / 2
	else:
		median = arranged[len(arranged)//2]

	if len(strings) > 0:				##check for bad inputs
		for index in range(len(strings)):
			if len(strings[index]) % 4 == 0:			##string length divisible by 4
				strings[index] = strings[index][::-1]
##****************************************************************************************

			if len(strings[index]) % 5 == 0:			##string length divisible by 5
				strings[index] = strings[index][:5]
##******************************************************************************************

			count = 0
			for char in strings[index][:5]:				##count number of capitals in first 5
				if char == char.upper():
					count += 1
			if count >= 3:								##make word a capital if true
				strings[index] = strings[index].upper()
				count = 0
##**********************************************************************************************

			if strings[index][len(strings[index])-1:] == "-":	##check for hyphen
				if index == len(strings) - 1:					##if there is no next word, just remove hyphen
					strings[index] = strings[index][:len(strings)-1]
				else:
					strings[index] = strings[index][:len(strings[index])-1]
					strings[index] += strings[index + 1]
##**********************************************************************************************

			print (strings[index])

	end_chars = count_chars(strings)
	print("Starting chars: " + str(start_chars) + "\nEnding Chars: " + str(end_chars) + "\nMedian length of input: " + str(median))

def count_chars(strings):		##counts chars in a list
	total = 0
	for string in strings:
		total += len(string)
	return total

string_list_editor(["hellothere","name","YELling","hyphen-","ate"])		##sample input with one case for each objective