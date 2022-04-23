
/*
 * PageTable
 */

 /*
 * Para o propósito dessa simulação, a tabela de páginas será
 * o arquivo .trace utilizado, dessa forma simulando o acesso à 
 * memória em um tlb miss 
 */

public class PageTable {
    private FileHandler file; //tabela de paginas
    
    public PageTable(String filename){
        file = new FileHandler(filename);
        file.openFile();
    }

    public Pair getNextPage() {
        return file.readPage();
    }

}