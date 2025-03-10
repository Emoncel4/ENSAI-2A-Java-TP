package fr.ensai.library;

public class Magazine extends Item{
    private String issn;
    private String issueNumber;

    public Magazine(String issn, String issueNumber, String title, int year, int pageCount){
        super(title, year, pageCount);
        this.issn = issn;
        this.issueNumber = issueNumber;
    }

    public String toString(){
        return "issn : " + issn + " issueNumber : " + issueNumber;
    }
}
