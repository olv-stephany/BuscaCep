import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Historico{
    private static ArrayList<String> registros = new ArrayList<>();

    public static void adicionar(String registro){
        registros.add(registro);
    }
    
    public static void salvar() throws IOException {

        File pasta = new File("export");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        FileWriter arquivo = new FileWriter("export/historico.csv", true);
        for (String registro : registros) {
            arquivo.write(registro + "\n");
        }
        arquivo.close();
    }
}