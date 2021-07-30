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
    document.getElementById("loader").classList.remove("hidden");    
    const response = await fetch('/' + temp[1] + 'Questions?end=' + queryEnd);
    const data = await response.json();
    formatQuestions(data, "searchSection");
        document.getElementById("loader").classList.add("hidden");
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

// Makes request for most frequently asked questions
async function faqTemplateData() {
    const response = await fetch('/mostFrequentQuestion');
    const data = await response.json();    

    formatQuestions(data, "faqQuestionSection");
    document.getElementById("loader").style.display = "none";
}

/**
 * Formats the questions requested from the server
 * @param {JSON} data - holds the questions that were requested from server 
 * @param {String} sectionID - the location to put the questions
 */
function formatQuestions(data, sectionID) {
    for (var i = 0; i < data.length; i++) {
        let questionDiv = document.createElement("div");
        let a = document.createElement("a");
        let p = document.createElement("p");
        let hr = document.createElement("hr");

        a.textContent += "Question: " + data[i].QUESTION;
        p.textContent = "Device Type: " + data[i].DEVICE_TYPE;
        a.setAttribute('href', "QA.html?" + data[i].QUESTION_ID);

        questionDiv.appendChild(a);
        questionDiv.appendChild(p);
        questionDiv.appendChild(hr);

        document.getElementById(sectionID).appendChild(questionDiv);
    }
}

async function QAdata() {
    var parameters = location.search.substring(1).split("&");
    var temp = parameters[0].split("=");

    console.log(temp[0]);

    const response = await fetch('/questionAndAnswer?question_ID=' + temp[0]);

    const data = await response.json();

    document.getElementById('question').innerText = data[0].DATA;
    document.getElementById('device_type').innerText = data[1].DATA;

    var temp, item, a, i;
    temp = document.getElementById('QATemplate');
    question = temp.content.querySelector('p');

    for (i = 2; i < data.length; i++) {
        a = document.importNode(question, true);
        a.textContent += data[i].DATA;
        document.getElementById("QASection").appendChild(a)
    }

    document.getElementById("loader").classList.add("hidden");
}

async function submitAnswer() {
    var parameters = location.search.substring(1).split("&");
    var temp = parameters[0].split("=");

    console.log(temp[0]);

    var answer = document.getElementById("answer").value

    await fetch('/addAnswer?question_ID=' + temp[0]
        + '&answer=' + answer);

    location.reload();
}
