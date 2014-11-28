import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.util.List;

/**
 * A class to hold details of audio files.
 * This version can play the files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
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

    static public ArrayList<String> getMP3filesFromDir(){
        String folderPath = getFolderPath();
        List<String> st = new java.util.Vector<String>();
        //        List<String> strings = (ArrayList<String>) java.util.Arrays.asList(new java.io.File(folderPath).list());
        String[] strings = new java.io.File(folderPath).list();
        ArrayList<String> mp3Files = new ArrayList<String>();
        for (String s : strings) {
            if (s.endsWith("mp3")){
                mp3Files.add(folderPath + System.getProperty("file.separator") + s);
            }
        }
        return mp3Files;
    }
}
