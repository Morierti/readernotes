/**
Copyright (C) 2016  Rodrigo Ramos Rosa

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
**/

package readernotes.src.ui.listeners;

// Lib Imports.
import java.awt.event.ActionEvent;

// Application Imports
import readernotes.src.core.Book;
import readernotes.src.ui.BookForm;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class BookFormListener
extends FormListener {
    private BookForm _bookForm;
    private String _title;
    private String _author;
    private String _isbn;
    private String _subject;
    private String _sinopse;
    
    public BookFormListener(BookForm bookForm) {
        this.setBookForm(bookForm);                    
    }
    
    private void setBookForm(BookForm bookForm) {
        _bookForm = bookForm;
    }
    
    public BookForm getBookForm() {
        return _bookForm;
    }
    
    private void setTitle(String title) {
        _title = title;
    }
    
    public String getTitle() {
        return _title;
    }
    
    private void setAuthor(String author) {
        _author = author;
    }
    
    public String getAuthor() {
        return _author;
    }
    
    private void setISBN(String isbn) {
        _isbn = isbn;
    }
    
    public String getISBN() {
        return _isbn;
    }
    
    private void setSubject(String subject) {
        _subject = subject;
    }
    
    public String getSubject() {
        return _subject;
    }
    
    private void setSinopse(String sinopse) {
        _sinopse = sinopse;
    }
    
    public String getSinopse() {
        return _sinopse;
    }
    
    @Override
    public void setup() {
        this.setTitle(this.parseScrollPane(getBookForm().getTitleArea()));
        this.setAuthor(this.parseScrollPane(getBookForm().getAuthorArea()));
        this.setISBN(this.parseScrollPane(getBookForm().getISBNArea()));
        this.setSubject(this.parseScrollPane(getBookForm().getSubjectArea()));
        this.setSinopse(this.parseScrollPane(getBookForm().getSinopseArea())); 
    }
    
    @Override
    public void execute(ActionEvent event) {
        try {
		    Book book = getBookForm().getBook();
			if (this.getTitle() != null) {
				book.setTitle(this.getTitle());
			}
			if (this.getAuthor() != null) {
				book.setAuthor(this.getAuthor());
			}
			if (this.getSinopse() != null) {
				book.setSinopse(this.getSinopse());
			}
			if (this.getISBN() != null) {
				book.setISBN(this.getISBN());
			}
			if (this.getSubject() != null)   {
				book.setSubject(this.getSubject());
			}

		} catch (EmptyTitleException
				| EmptyAuthorException exception) {
			System.err.print(exception.getMessage());
		}
		getBookForm().dispose();
    }
             
    

}
