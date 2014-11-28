import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.util.List;
import java.io.File;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.mp3.*;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.id3.*;

/**
 * A class to hold details of audio files.
 * This version can play the files.
 * 
 * @author Andrej
 * @version 2014.11.27
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;
    // A player for the music files.
    private MusicPlayer player;

    static public void main (String [] abc){
        MusicOrganizer mo = new MusicOrganizer();

        mo.files = mo.getMP3filesFromDir();
        mo.listAllFiles();
        mo.startMusicOrganizer();

    }

    /**
     * Start point for player
     */
    public void startMusicOrganizer(){

        while(true) {
            // din kod här
            System.out. print("Wich track to play? Enter a number: ");
            String input = TextIO.getln();
            String trace = "";

            if (input.matches("\\d+$")) { // testa om input består av en eller flera siffror
                int index = Integer.parseInt(input);
                if (index > 0 && index <= this. files.size()){
                    this.startPlaying(index-1);
                }
            } else {
                switch (input){
                    case "exit": 
                    case "q":
                    System.out.println("Auf Wiedersehen!");
                    System.exit(0);
                    break;
                    case "stop": 
                    case "s":
                    this.stopPlaying();
                    break;
                    case "list": 
                    case "l":
                    this.listAllFiles();
                    break;

                    case "edit":
                    System.out.println("What would you like to edit? Genre or year?");
                    String what = TextIO.getln();
                    switch(what) {
                        case "year":
                        System.out.println("What year?");
                        String year = TextIO.getln();
                        this.editFile(Integer.parseInt(trace), year, "");
                        break;

                        case "genre":
                        System.out.println("What genre?");
                        String genre = TextIO.getln();
                        this.editFile(Integer.parseInt(trace), "", genre);
                        break;
                    }
                    break;

                    default:
                    System.out.println("Did you really wrote \"" + input + "\"!?");

                }
            }
        }
    }

    /**
     * Test method
     */
    static public void test(){
        MusicOrganizer mo = new MusicOrganizer();
        loadFiles (mo);
        mo.listAllFiles();
    }

    static public void loadFiles (MusicOrganizer mo){
        mo.addFile("audio/BigBillBroonzy-BabyPleaseDontGo1.mp3");
        mo.addFile("audio/BlindBlake-EarlyMorningBlues.mp3");
        mo.addFile("audio/BlindLemonJefferson-matchBoxBlues.mp3");
        mo.addFile("audio/BlindLemonJefferson-OneDimeBlues.mp3");
    }

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<String>();
        player = new MusicPlayer();
    }

    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename)
    {
        files.add(filename);
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }

    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(index >= 0 && index < files.size()) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(index >= 0 && index < files.size()) {
            files.remove(index);
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        String filename = files.get(index);
        stopPlaying();
        player.startPlaying(filename);
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }

    /**
     * Lists all files in the music library.
     */
    public void listAllFiles()
    {
        int position = 0;
        for(int i = 0; i < files.size(); i++){
            String fileName = files.get(i);
            int indexOfTheLastSlash = fileName.lastIndexOf("/");
            position = files.indexOf(fileName);
            System.out.println((position+1) + ": " + fileName.substring(indexOfTheLastSlash+1));
            //             position++;
        }
    }

    /**
     * Seach files (aka 4.26)
     * 
     */

    public void listMatching (String str){
        boolean noHit = true;
        for(String s : files){
            if(s.contains(str)){
                int indexOfTheLastSlash = s.lastIndexOf("/");
                System.out.println(files.indexOf(s) + ": " + s.substring(indexOfTheLastSlash+1));
                noHit = false;
            }
        }

        if(noHit){
            System.out.println("No match");
        }
    }

    static public String getFolderPath(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().toString();
        } 
        return null;
    }

    public ArrayList<String> getMP3filesFromDir(){
        String folderPath = getFolderPath();
        ArrayList<String> mp3Files = new ArrayList<String>();
        mp3Files.addAll( addFiles( folderPath ) );
        return mp3Files;
    }

    private ArrayList<String> addFiles(String dir){
        // System.out.println("addFiles from: " + dir);
        ArrayList<String> mp3Files = new ArrayList<String>();
        String[] strings = new java.io.File(dir).list();
        if (strings != null && strings.length > 0){
            // loop through strings
            for (String fileOrDir : strings){
                String workingOn = dir + System.getProperty("file.separator") +fileOrDir;
                // if .mp3 - add to newArrayList
                if (workingOn.toLowerCase().endsWith(".mp3")){
                    mp3Files.add(workingOn);
                    // System.out.println("added: " + workingOn);
                }
                // if directory - newArrayList = addFiles from new dir;
                if (new File(workingOn).isDirectory()){
                    // System.out.println("inspecting: " + workingOn);
                    mp3Files.addAll(addFiles(workingOn));
                }
            }
        }
        return mp3Files;
    }

    public void editFile  (int index, String year, String type) {
        String filePath = files.get(index-1);
        try {
            AudioFile f = AudioFileIO.read(new java.io.File(filePath));
            Tag tag = f.getTag();

            if (year.length() > 0) {
                tag.setField(FieldKey.YEAR, year);
            }
            else if (type.length() > 0) {
                tag.setField(FieldKey.GENRE, type);
            }
            AudioFileIO.write(f);
        }
        catch (Exception e) {
        }
    }
}

