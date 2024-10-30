package songpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class program5 {
    public static void main(String[] args) {
        // Specify the path to your TSV file and the genre you want to read
        String tsvFilePath = args[0]; // Change this to your actual file path
        String genre = "rock"; // Change this to your favorite genre

        try {
            // Read the songs into a BinarySearchTree
            BinarySearchTree songsBST = MyDataReader.readFileToBST(tsvFilePath, genre);
            System.out.println("Songs loaded into the BST successfully!");
            
            Top10Songs(songsBST);
            
            mostPopularArtistInRange(songsBST, 1000, 2000000);

        } catch (IOException e) {
            System.err.println("Error reading the TSV file: " + e.getMessage());
        }
        
    }
    
    private static void Top10Songs(BinarySearchTree songs) {
    	// Get the sorted list of songs
        ArrayList<Song> sortedSongs = songs.toSortedArrayList();
        // Reverse the list to get descending order by views
        Collections.reverse(sortedSongs);
        // Print the top 10 songs with the highest views
        System.out.println("Top 10 songs with the highest views:");
        for (int i = 0; i < 10 && i < sortedSongs.size(); i++) {
            Song song = sortedSongs.get(i);
            System.out.println(song.getTitle() + " - Views: " + song.getViews());
        }
    }
    private static void mostPopularArtistInRange(BinarySearchTree songs, int rangeMin, int rangeMax) {
    	// Clone the tree
        BinarySearchTree clonedTree = songs.clone();

        // Filter nodes with views < 1000 or > 10000 on the cloned tree
        clonedTree.fliterByView(rangeMin, rangeMax);

        // Check if the resulting cloned tree is still a valid BST
        System.out.println("Is the filtered cloned tree a valid BST? " + clonedTree.isBST());

        // Print the most popular artists within the given view range
        System.out.println("Most popular artist(s) within the view range " + rangeMin + " - " + rangeMax + ":");
        for (String artist : clonedTree.popularArtists()) {
            System.out.println(artist);
        }
    }
}
