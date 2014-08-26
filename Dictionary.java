import java.util.*;
public class Dictionary
{
 
    private ArrayList <Word> words;
    private FileInput in;
    private FileOutput out;
    
    public Dictionary(String fileName)
    {
        in=new FileInput(fileName);
        out=new FileOutput(fileName,true);
        words=new ArrayList<Word>(); 
    }
 
    public void setDictionary()
    {
        Word word;   
        while(in.hasNextLine())
        {
            word=new Word(in.nextLine()); 
            words.add(word);
        } 
        in.close();
    }  
 
    public void closeOutput()
    { 
        out.close();
    }
    
    public int findWord(String token)
    {
        int flag=0;
        for(Word word:words)
        if(word.getWord().equalsIgnoreCase(token))
        {
            flag++;
            break;
        }
        return flag;
    }
 
    public ArrayList<String> getSuggestions(String textWord)
    {
        ArrayList<String> suggestions=new ArrayList<String>();
        for(Word word:words)
        if(getDistance(word.getWord(),textWord)==1) suggestions.add(word.getWord());
        return suggestions;
    }
 
    public void addWordToDictionary(String word)
    {
        out.writeString("\n");
        out.writeString(word);
    }
    
    public ArrayList<Word> getWords()
    {
        return  words;
    }

    public int getDistance(String first,String second) //Implementation of Damerau-Levenshtein Distance algorithm, for 2 strings
    {
     int m,n,colCount,rowCount,cost;
     first=first.toLowerCase();
     second=second.toLowerCase();
     m=first.length();
     n=second.length();
     int[][] d=new int [m+1][n+1];
   
     for(colCount=0;colCount<=m;colCount++)
        d[colCount][0]=colCount;  
     for(rowCount=0;rowCount<=n;rowCount++)
        d[0][rowCount]=rowCount; 
 
     for(colCount=1;colCount<=m;colCount++)
     {
       for(rowCount=1;rowCount<=n;rowCount++)
       {
        if(first.charAt(colCount-1)==second.charAt(rowCount-1))
          cost=0; 
          else cost=1;
        d[colCount][rowCount]=Math.min(Math.min(
                         d[colCount-1][rowCount]+1,
                         d[colCount][rowCount-1]+1),
                         d[colCount-1][rowCount-1]+cost
                         );
        if(colCount>1 && rowCount>1 && first.charAt(colCount-1)==second.charAt(rowCount-2)
        && first.charAt(colCount-2)==second.charAt(rowCount-1))
        
        d[colCount][rowCount]=Math.min(d[colCount][rowCount],d[colCount-2][rowCount-2]+cost);
        }
     }
     return d[m][n];
    }

}
