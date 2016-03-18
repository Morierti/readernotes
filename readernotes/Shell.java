package readernotes;

import readernotes.src.core.Book;
import readernotes.src.database.BookXML;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;
import java.io.IOException;

public class Shell {
	
	public static void main(String[] args) 
	throws
	EmptyTitleException,
	EmptyAuthorException,
	IOException {
		Book newBook = new Book("A Mensagem", "Fernando Pessoa");
		newBook.setSinopse("Um livro sobre livros.");
		BookXML xmlBook = new BookXML(newBook);

		xmlBook.buildDocument();
		xmlBook.printDocument();
	}

}