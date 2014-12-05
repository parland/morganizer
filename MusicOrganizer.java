import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.util.List;
import java.io.File;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.mp3.*;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.id3.*;
import java.util.Random;

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
    private ArrayList<Track> tracks;
    // A player for the music files.
    private MusicPlayer player;
    private String userName;
    
    static public void main (String [] abc){
        MusicOrganizer mo = new MusicOrganizer();
        mo.tracks = new ArrayList<Track>();
        mo.files = mo.getMP3filesFromDir();
        if (mo.tracks == null) System.out. println("mo.tracks är null");
        for ( String fileName : mo.files){
            System.out. println(fileName);
            Track t = new Track(fileName); 
            mo.tracks.add(t);
        }
        mo.listAllFiles();
        mo.startMusicOrganizer();
    }

    /**
     * Start point for player
     */
    public void startMusicOrganizer(){

        while(true) {
            
            System.out. print("Wich track to play? Enter a number: ");
            String input = TextIO.getln();
            String trace = null;
            if (input.contains(" ")) {
                trace = input.substring(input.indexOf(" ")+1);
                input = input.substring(0, input.indexOf(" "));
            }
            int trackIndex = 0;
            if (input.matches("\\d+$")) { // testa om input består av en eller flera siffror
                trackIndex = Integer.parseInt(input);
                if (trackIndex > 0 && trackIndex <= this. files.size()){
                    this.startPlaying(trackIndex-1);
                }
            } else {
                switch (input){
                    case "exit": 
                    case "q":
                    System.out.println("Auf Wiedersehen!");
                    System.exit(0);
                    break;

                    case "play": 
                    case "p":
                    this.playTrack(trace);
                    break;

                    case "stop": 
                    case "s":
                    this.stopPlaying();
                    break;

                    case "list": 
                    case "l":
                    this.listAllFiles();
                    break;

                    case "help":
                    case "?":
                    this.listAllCommands();
                    break;

                    case "rand":
                    case "r":
                    this.playRandom();
                    break;

                    case "find":
                    case "f":
                    this.listMatching(trace);
                    break;

                    case "save":
                    this.saveTrack(trace);
                    break;

                    case "load":
                    this.loadLibrary("lib.txt"); //correct file name
                    break;

                    case "info":
                    if(trace == null) {
                        System.out.println("USAGE: info [trackIndex]");
                        break;
                    }
                    try {
                        trackIndex = Integer.parseInt(trace);
                    } catch (NumberFormatException e){
                        System.out.println("USAGE: info [trackIndex] as an integer");
                        break;
                    }
                    this.mp3FileInfo(trackIndex);
                    break;

                    case "edit":
                    if(trace == null) {
                        System.out.println("USAGE: edit [trackIndex]");
                        break;
                    }
                    try {
                        trackIndex = Integer.parseInt(trace);
                    } catch (NumberFormatException e){
                        System.out.println("USAGE: edit [trackIndex] as an integer");
                        break;
                    }
                    System.out.println("What would you like to edit? Genre or year?");
                    String what = TextIO.getln();
                    switch(what) {
                        case "year":
                        System.out.println("What year?");
                        String year = TextIO.getln();
                        this.editFile(trackIndex, year, "");
                        break;

                        case "genre":
                        System.out.println("What genre?");
                        String genre = TextIO.getln();
                        this.editFile(trackIndex, "", genre);
                        break;
                    }
                    break;

                    default:
                    System.out.println("Did you really wrote \"" + input + "\"!?");

                }
            }
        }
    }

    @Deprecated 
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
        for (Track tr : tracks){
            if (tr.getFileName().equals(filename)){
                tr.incrementCount();
            }
        }
        System.out.println("Now Playing " + this .getCleanFileName(files.get(index)));
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
            //             String fileName = files.get(i);
            Track tr = tracks.get(i);
            //position = files.indexOf(fileName);
            System.out.println((position+1) + ": " +" play count = "+ tr.getPlayCount() 
                + " >>> " + this. getCleanFileName(tr.getFileName()));
            position++;
        }
    }

    /**
     * Seach files (aka 4.26)
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
        } catch (Exception e) {
        }
    }

    public void mp3FileInfo (int index) {
        String filePath = files.get(index-1);
        try {
            MP3File f = (MP3File)AudioFileIO.read(new java.io.File(filePath));
            MP3AudioHeader audioHeader = f.getMP3AudioHeader();
            System.out.println("Length: " + audioHeader.getTrackLength() + " sec");

            // does not work for now
            //             AbstractID3v2Tag v2Tag = f.getID3v2Tag();
            //             System.out.println("Artist: " + v2Tag.getFirst(ID3v24Frames.FRAME_ID_ARTIST));
            //             System.out.println("Album: " + v2Tag.getFirst(ID3v24Frames.FRAME_ID_ALBUM));
            //             System.out.println("Year: " + v2Tag.getFirst(ID3v24Frames.FRAME_ID_YEAR));

            Tag tag = f.getTag();
            System.out.println("Genre: " + tag.getFirst(FieldKey.GENRE));
            System.out.println("Artist: " + tag.getFirst(FieldKey.ARTIST));
            System.out.println("Album: " + tag.getFirst(FieldKey.ALBUM));
            System.out.println("Year: " + tag.getFirst(FieldKey.YEAR));

        } catch (Exception e) {
        }
    }

    public void listAllCommands() {
        System.out.println("exit OR q FOR quit");
        System.out.println("list OR l FOR list of all tracks");
        System.out.println("stop OR s FOR stop playing");
        System.out.println("rand OR r FOR random track");
        System.out.println("find OR f AND [seachString] FOR track seach ");
        System.out.println("save AND [fileName] TO save play list");
        System.out.println("edit AND [trackIndex] FOR edit mp3 tags");
        System.out.println("info AND [trackIndex] FOR track information");
        System.out.println("help OR ? FOR this list");
    }

    /**
     * Play random track
     */
    public void playRandom()
    {   
        Random r = new Random();
        startPlaying(r.nextInt(files.size()));
    }  
   
    private void saveTrack(String trace){
        String fileName = null;
        if(trace == null){
            System.out.println("and filename is: ");
            fileName = TextIO.getln();
            
        }
        else {
            fileName = trace;
        }
      if(fileName != ""){
            this.saveMusicLibrary(fileName);
            }
        
    }
    private void playTrack(String trace){
        int trackIndex = 0;
        if(trace == null) {
            System.out.println("USAGE: play [trackIndex]");
            return;
        }
        try {
            trackIndex = Integer.parseInt(trace);
            this.mp3FileInfo(trackIndex);
            this.startPlaying(trackIndex-1);

        } catch (NumberFormatException e){
            System.out.println("USAGE: play [trackIndex] as an integer");

        }
    }

    public void saveMusicLibrary(String fileName) {
        // String fileName = "lib.txt";

        TextIO.writeFile(fileName);
        for(String file : files) {
            TextIO.putln(file);
        }
    }

    public void loadLibrary (String libraryFileName){
        TextIO.readFile(libraryFileName);
        while(TextIO.peek() != TextIO.EOF) {
            this.files.add(TextIO.getln());
        }
        TextIO.readStandardInput();
    }
   
    private String getCleanFileName(String s){
        int indexOfTheLastSlash = s.lastIndexOf(System.getProperty("file.separator"));
        return s.substring(indexOfTheLastSlash+1);
    }

}

	