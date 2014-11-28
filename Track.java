
/**
 * Write a description of class Track here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Track
{
    private String artist;
    private String title;
    private String filename;   

    private int playCount;

    public Track (String filePath){
        filename = filePath;
    }

    public void setDetails(String artist, String title, String filename)
    {   
        setDetails("unknown","unknown",filename);
        playCount = 0;
    }

    public void resetCount(){
        playCount = 0;
    }

    public void incrementCount(){
        playCount++;
    }

    public String getFileName() {
        return filename;
    }
    
    public int getPlayCount(){
        return playCount;
    }
}
