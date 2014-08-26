------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE:SpellChecker
PURPOSE OF PROJECT:To do spellchecking on wrongly spelled words

HOW TO START THIS PROJECT:
Execute main function in Main class of the program

AUTHORS:Sandu Octav-Emilian

DESCRIPTION:
This is a spellchecker software, based on Levenshtein Damerau algorithm 
which finds "distances" between words.

It assumes that the user had an error of 1 or 2 wrong letters, which are the most 
commonly encountered cases in texts.

The program handles two files, the british dictionary and the text input, and
"learns" all the new words, by storing them in the dictionary.
It gives suggestions for words and it prints the correct text in the output file,
keeping the initial formatting.

USER INSTRUCTIONS:
*The classes Input, FileInput, FileOutput are written by Dr. Graham Roberts.

1)Copy files from Test Files directory in the folder of the program,
replacing textinput.txt with one of the test files provided.
2) Run the program and choose appropriate suggestions provided by 
the program by entering the number of the desired suggestion 
in the input dialog, or choose to add a new word to dictionary.
3)See the result in Result.txt
