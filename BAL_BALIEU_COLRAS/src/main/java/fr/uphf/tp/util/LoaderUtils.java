package fr.uphf.tp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import fr.uphf.tp.model.Room;
import fr.uphf.tp.model.Seat;

/**
 * Class Java LoaderUtils
 * 
 * @author Antoine BALIEU
 *
 */
public class LoaderUtils {

	/**
	 * Fonctiion qui retourne une pièce avec les sièges lus à partir du fichier passé en paramètre
	 * @param file Nom du fichier
	 * @return Room Une pièce avec les sièges lus
	 * @throws IOException
	 */
    public static Room fromFile(String file) throws IOException {
    	
        Set<Seat> seats = new HashSet<>();

        try {
            InputStream is = LoaderUtils.class.getClassLoader().getResourceAsStream(file);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");

                int id = Integer.parseInt(tokens[0]);
                int x = Integer.parseInt(tokens[1]);
                int y = Integer.parseInt(tokens[2]);

                seats.add(new Seat(id, x, y));
            }

            is.close();
            isr.close();
            br.close();
        } 
        finally {}

        return new Room(seats);
    }
}
