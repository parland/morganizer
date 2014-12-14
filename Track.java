import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.util.List;
import java.io.File;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.mp3.*;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.id3.*;


/**
 * Write a description of class Track here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Track
{
    public String artist;
    public String title;
    public String album;
    public String filename;
    public int year;
    public int playCount;

    public Track (String filePath){
        try {
           MP3File file = (MP3File)AudioFileIO.read(new File(filePath));
           //MP3AudioHeader audioHeader = file.getMP3AudioHeader();
           Tag tag = file.getTag();
           this.artist = tag.getFirst(FieldKey.ARTIST);
           this.album  = tag.getFirst(FieldKey.ALBUM);
           this.title = tag.getFirst(FieldKey.TITLE);
           this.year = Integer.parseInt(tag.getFirst(FieldKey.YEAR));
        } catch (Exception e) {
            this.title = filePath.substring(filePath.lastIndexOf("/"));
            this.artist = "unknown";
        }
        this.playCount = 0;
        this.filename = filePath;
    }

    public void setDetails(String artist, String title, String filename)
    {   
      //  this.artist = (artist.length() == 0) ? "unknown" : artist;
      //  this.title = (title.length() == 0 ) ? "unknown" :  title;
      //  this.playCount = 0;
        setDetails("unknown", "unknown", filename);
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
