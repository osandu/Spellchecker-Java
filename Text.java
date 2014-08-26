import java.util.*;
public class Text
{
    private ArrayList<String> textWords;
    private FileInput in;
 
    public Text(String fileName)
    {
        in=new FileInput(fileName); 
        textWords=new ArrayList<String>(); 
    }
 
    public void tokenizeText()
    {
        String input;
        StringTokenizer tokenizer;
   
        while (in.hasNextLine()) 
        {
          input=in.nextLine();  
          tokenizer=new StringTokenizer(input);//," .,!?;:"
          while(tokenizer.hasMoreTokens())
          {
            textWords.add(tokenizer.nextToken());
            //if(tokenizer.hasMoreTokens())System.out.println(tokenizer.nextToken());
          }
          textWords.add("#");
        }
    in.close();
   }
  
 public ArrayList<String> getTokens()
  {
      return textWords;
  }
}
