// Searches for a given search term.  If there are no results,
// navigates to no-results.html
async function search(term) {
    var askedQuestion = document.getElementById("search-bar").value;
    var words = askedQuestion.split(" ");
    var queryEnd = "";

    var parameters = location.search.substring(1).split("&");
    var temp = parameters[0].split("=");

    for (var i = 0; i < words.length; i++) {
        queryEnd += "question LIKE )(" + words[i] + "()";

        if (i < words.length - 1) {
            queryEnd += " OR ";
        }
    }

    const response = await fetch('/' + temp[1] + 'Questions?end=' + queryEnd);

    const data = await response.json();

    var temp, item, a, i;
    temp = document.getElementById('searchTemplate');
    question = temp.content.querySelector('a');

    for (i = 0; i < data.length; i++) {
        a = document.importNode(question, true);
        a.textContent += 'Question: ' + data[i].QUESTION;
        document.getElementById("searchSection").appendChild(a)
    }
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

async function faqTemplateData() {
    const response = await fetch('/mostFrequentQuestion');

    const data = await response.json();

    var temp, item, a, i;
    temp = document.getElementById('faqTemplate');
    question = temp.content.querySelector('h3');
    device_Type = temp.content.querySelector('p');

    for (i = 0; i < data.length; i++) {
        a = document.importNode(question, true);
        a.textContent += 'Question: ' + data[i].QUESTION;
        document.getElementById("faqQuestionSection").appendChild(a)

        a = document.importNode(device_Type, true);
        a.textContent += 'Device type: ' + data[i].DEVICE_TYPE;
        document.getElementById("faqQuestionSection").appendChild(a)
    }

    document.getElementById("loader").style.display = "none";
}
