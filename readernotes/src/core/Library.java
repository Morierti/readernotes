package readernotes.src.core;

import java.util.Map;
import java.util.HashMap;
import readernotes.src.database.IOManager;
import readernotes.src.database.SinteseXML;
import readernotes.src.database.BookXML;
import readernotes.src.exceptions.DoubleEntryException;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentSinteseException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class Library {
    private static Library _instance;
    private IOManager _ioManager;
    private Map<String, Book> _bookDB;
    private Map<String, Sintese> _sinteseDB;
    
    public static boolean isEmpty() {
        return _instance == null;
    }

    public static Library getInstance() {
        if (_instance.isEmpty()) {
            new Library();
        }
        return _instance;
    }

    private Library() {
        init();
    }

    private void init() {
        _instance = this;
        _ioManager = IOManager.getInstance();
        this.loadBookDatabase();
        this.loadSinteseDatabase();
    }
    
    public void addBook(Book newBook)
    throws
    DoubleEntryException {
        if (_bookDB.containsKey(newBook.getTitle())) {
            throw new DoubleEntryException(newBook.getTitle());
        } else {
            _bookDB.put(newBook.getTitle(), newBook);
        }
    }
    
    public void removeBook(String bookTitle) {
        _bookDB.remove(bookTitle);
    }
    
    public Book getBook(String bookTitle)
    throws
    InexistentBookException {
        if (_bookDB.containsKey(bookTitle)) {
            return _bookDB.get(bookTitle);
        } else {
            throw new InexistentBookException(bookTitle);
        }
    }
    
    public Map<String, Book> getBookDB() {
        return _bookDB;
    }
    
    public void addSintese(Sintese newSintese)
    throws
    DoubleEntryException {
        if (_sinteseDB.containsKey(newSintese.getTitle())) {
            throw new DoubleEntryException(newSintese.getTitle());
        } else {
            _sinteseDB.put(newSintese.getTitle(), newSintese);
        }
    }
    
    public void removeSintese(String title) {
        _sinteseDB.remove(title);
    }
    
    public Sintese getSintese(String sinteseTitle)
    throws
    InexistentSinteseException {
        if (!_sinteseDB.containsKey(sinteseTitle)) {
            throw new InexistentSinteseException(sinteseTitle);
        } else {
            return _sinteseDB.get(sinteseTitle);
        }
    }
    
    public Map<String, Sintese> getSinteseDB() {
        return _sinteseDB;
    }

	public void storeBookDatabase() {
		try {
			_ioManager.buildBookDBObjects();
			_ioManager.writeBookDatabaseDocument();
		} catch (InexistentBookException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public void storeSinteseDatabase() {
		try {
			_ioManager.buildSinteseDBObjects();
			_ioManager.writeSinteseDatabaseDocument();
		} catch (InexistentSinteseException exception) {
			System.err.println(exception.getMessage());
		}
	}
    
    public void loadBookDatabase() {
        try {
            _bookDB = _ioManager.buildBookDatabase();
        } catch (EmptyTitleException | EmptyAuthorException exception) {
            System.err.println(exception.getMessage());
        }
    }
    
    public void loadSinteseDatabase() {
        try {
            _sinteseDB = _ioManager.buildSinteseDatabase();
        } catch (EmptyTitleException exception) {
            System.err.println(exception.getMessage());
        }
    }
    
}
