import java.util.*;
import javax.swing.JOptionPane;
import java.lang.*;

public class SpellChecker
{
    //declaring instance variables
    private Text text;
    private Dictionary dictionary;
    private FileOutput output;

    private ArrayList<String> fileBuffer=new ArrayList<String>();
    //declaring constructor
    public SpellChecker()
    {
        dictionary=new Dictionary("brit-a-z.txt");
        text=new Text("textinput.txt");
        output=new FileOutput("result.txt");
    }
    
    public void printOptions(String textWord)              //printing options to the console
    {
        int counter=0;
        ArrayList<String> suggestions=dictionary.getSuggestions(textWord);
            for(String suggestion:suggestions)
            {
                counter++;
                if(firstLetterIsCapital(textWord)) 
                 {
                     System.out.println(counter+"."+getCapitalised(suggestion));
                 }
                    else System.out.println(counter+"."+suggestion);
            }
        System.out.println((counter+1)+".Leave as it is.The word is correct");
        System.out.println((counter+2)+".Neither are right.I want to add a new word");
    }
    
    public String getChoice(int length)          //this function gets the input from the user and handles exceptions
    {
       try{
           String choice;
            do{
                do{
                    choice=JOptionPane.showInputDialog("Please enter the number of the suggestion you would like to use"); 
                    if(choice.equals("stop")) 
                    {
                       closeAll();
                    }
                } while(isNumber(choice)==false);
            } while(Integer.parseInt(choice)>length+2 || Integer.parseInt(choice)<=0);
           return choice;
       }
       catch (Exception e) {
           closeAll();
           System.exit(0);
           }
     return null;
    }
    
    public void closeAll()    //saves the contents of the corrected text to file and closes all open files
     {
         printToFile();
         output.close();
         dictionary.closeOutput();
         System.exit(0);
     }
    
    public boolean firstLetterIsCapital(String check)
    {
        char[] chars = check.toCharArray();
        if (Character.isUpperCase(chars[0])) return true;
            else return false;
    }

    public String getCapitalised(String check)
    {
        char[] chars=check.toCharArray();
        if(chars.length!=0)
             chars[0]=Character.toUpperCase(chars[0]);
        String capitalised = new String(chars);
      return capitalised;
    }

    public boolean isNumber(String input) 
    {
        try {
            Integer.parseInt(input);
            return true;
            }
       catch (NumberFormatException e) {
            return false;
       }
    } 
    
    public void initialiseObjects()
    {
        dictionary.setDictionary();
        text.tokenizeText();
    }
   
    public void printToFile()
    {
        for(String item:fileBuffer)
        {
                if(item.equals("#")) 
                {
                    output.writeString("\n");
                }
                else output.writeString(item+" ");
        }
    }
    
    public void closeOutput()
    {
        try{
            output.close();
           }
        catch(Exception e) { System.exit(1);}
    }
    
    public String youChose(String choice,String textWord)  //Function which handles the special case when the user inputs his own word as a suggestion
    {                                                         //which is also saved in the dictionary
        int length=dictionary.getSuggestions(textWord).size();
        
        if(Integer.parseInt(choice)>=0 &&Integer.parseInt(choice)<length) 
            System.out.println("\n You have chosen: "+dictionary.getSuggestions(textWord).get(Integer.parseInt(choice)-1)+"\n");
        
        if(Integer.parseInt(choice)>length) {
            String word=JOptionPane.showInputDialog("Please enter the word");
            dictionary.addWordToDictionary(word);
            System.out.println("\n You have chosen: "+word);
            return word;
        }
        
        return null;
    }   
    
    public void addToBuffer(String choice,String textWord,String word)
    {
        int length=dictionary.getSuggestions(textWord).size();
        
        if(Integer.parseInt(choice)<length)
            {
                if(firstLetterIsCapital(textWord)) 
                   {
                       fileBuffer.add(getCapitalised(dictionary.getSuggestions(textWord).get(Integer.parseInt(choice)-1)));
                   }
                else  fileBuffer.add(dictionary.getSuggestions(textWord).get(Integer.parseInt(choice)-1)); 
             }
         
         else if(Integer.parseInt(choice)==length+2) 
                 fileBuffer.add(word);
    }
    
    public void go()  //function which assembles all the bits of the program
    {
        initialiseObjects();
        ArrayList<String> tokens=text.getTokens();
        System.out.println("Possible suggestions for the wrongly spelled words \n");
        int counter=0;
     for(String textWord:tokens)
      {
        String choice,word;
        int length;
        if(dictionary.findWord(textWord)==0 &&!isNumber(textWord))
        {
                System.out.println("\n Suggestions for "+ textWord);
                length=dictionary.getSuggestions(textWord).size();
                printOptions(textWord);  
                choice=getChoice(length);
                
                if(Integer.parseInt(choice)==length+1) 
                {
                    fileBuffer.add(textWord);
                    continue;
                }
                
                word=youChose(choice,textWord);
                addToBuffer(choice,textWord,word);
                counter+=1;
        }
         else fileBuffer.add(textWord);
      } 
      System.out.println("\n Correcting process finished");
       if(counter==0) System.out.println("The text appears to be correct-no suggestions");
     printToFile();
     closeOutput();
    
    }

}