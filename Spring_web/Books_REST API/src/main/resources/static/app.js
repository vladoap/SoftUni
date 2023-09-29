function loadBooks() {
    fetch('/api/books')
        .then(response => response.json())
        .then(data => {
            const bookListTable = document.getElementById('bookListTable');
            bookListTable.innerHTML = ''; // Clear existing table content
            data.forEach(book => {
                const row = document.createElement('tr');
                row.innerHTML = `
                            <td>${book.title}</td>
                            <td>${book.author.name}</td>
                            <td>${book.isbn}</td>
                            <td>
                                <button class="edit-book" data-book-id="${book.id}">Edit</button>
                                <button class="delete-book" data-book-id="${book.id}">Delete</button>
                            </td>
                        `;
                bookListTable.appendChild(row);

                const deleteButton = row.querySelector('.delete-book');
                deleteButton.addEventListener('click', () => deleteBook(book.id));

                const editButton = row.querySelector('.edit-book');
                editButton.addEventListener('click', () => editBook(book.id));
            });
        })
        .catch(error => console.error('Error:', error));
}
// Add a click event listener to the "LOAD ALL BOOKS" button
document.getElementById('loadBooks').addEventListener('click', loadBooks);





    function addBook() {
    // Collect form data
    const title = document.getElementById('title').value;
    const authorName = document.getElementById('author').value;
    const isbn = document.getElementById('isbn').value;

    //mapping fields to BookDto
    const formData = {
        title: title,
        author: {
            name: authorName // Include the author's name within an AuthorDto object
        },
        isbn: isbn
    };

    // Send a POST request using the Fetch API
    fetch('/api/books/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
        .then(response => {
            if (response.ok) {
                // Handle successful response (e.g., show a success message)
                console.log('Book created successfully.');
                // You can redirect or perform other actions here.
            } else {
                // Handle error response (e.g., display an error message)
                console.error('Error creating book.');
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}
document.getElementById('addBookForm').addEventListener('submit', addBook);



    function deleteBook(bookId) {

        const id = parseInt(bookId);

        fetch(`/api/books/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (response.ok) {
                    // Handle successful response (e.g., remove the row from the table)
                    const rowToDelete = document.querySelector(`[data-book-id="${bookId}"]`).parentNode.parentNode;
                    rowToDelete.remove();
                    console.log('Book deleted successfully.');
                } else {
                    // Handle error response (e.g., display an error message)
                    console.error('Error deleting book.');
                }
            })
            .catch(error => console.error('Fetch error:', error));
    }


function editBook(bookId) {

    const editForm = document.getElementById('updateBookForm');
    const id = parseInt(bookId);
    // Fetch the book details by its ID
    fetch(`/api/books/${id}`)
        .then(response => response.json())
        .then(book => {
            // Populate the form fields with the book details
            editForm.querySelector('#titleU').value = book.title;
            editForm.querySelector('#authorU').value = book.author.name;
            editForm.querySelector('#isbnU').value = book.isbn;
            // Add an event listener to the form for submitting edits
            editForm.addEventListener('submit', event => {
                event.preventDefault(); // Prevent the default form submission
                // Collect the updated book data from the form
                const updatedData = {
                    title: editForm.querySelector('#titleU').value,
                    author: {
                        name: editForm.querySelector('#authorU').value,
                    },
                    isbn: editForm.querySelector('#isbnU').value,
                };
                // Send a PUT request to update the book
                fetch(`/api/books/update/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(updatedData),
                })
                    .then(response => {
                        if (response.ok) {
                            // Handle successful response (e.g., display a success message)
                            console.log('Book updated successfully.');
                            // You can redirect or perform other actions here.
                        } else {
                            // Handle error response (e.g., display an error message)
                            console.error('Error updating book.');
                        }
                    })
                    .catch(error => console.error('Fetch error:', error));
            });
        })
        .catch(error => console.error('Fetch error:', error));
}
