
/*
 * PageTable
 */

 /*
 * Para o propósito dessa simulação, a tabela de páginas será
 * o arquivo .trace utilizado, dessa forma simulando o acesso à 
 * memória em um tlb miss 
 * 
 * O tempo de acesso a memória considerado, será o tempo da execução de
 * abrir o arquivo .trace, ler um numero de pagina e fechar o arquivo.
 * 
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

    public double getAccessTime() {
        long begin = System.nanoTime();
        file.openFile();
        file.readPage();
        file.closeFile();
        long end = System.nanoTime();
        return (end-begin);
    }

}