// Searches for a given search term.  If there are no results,
// navigates to no-results.html
function search(term) {
    window.location.href = "no-results.html";
}

// Adds user's question to database
async function askQuestions() {
    // TODO: add question to database
    var email = document.getElementById('email').value;
    var device_Type = document.getElementById('device_type').value;
    var question = document.getElementById('question').value;

    await fetch("/addQuestion?email=" + email
        + "&question=" + question
        + "&device_type=" + device_Type);
}
