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

async function faqTemplateData() {
    const response = await fetch('/mostFrequentQuestion');

    const data = await response.json();

    var temp, item, a, i;
    temp = document.getElementById('faqTemplate');
    question = temp.content.querySelector('h3'); 
    device_Type = temp.content.querySelector('p');

    console.log(data); 

    for (i = 0; i < data.length; i++) {
        a = document.importNode(question, true);
        a.textContent += 'Question: ' + data[i].QUESTION;
        document.body.appendChild(a);

        a = document.importNode(device_Type, true);
        a.textContent += 'Question: ' + data[i].DEVICE_TYPE;
        document.body.appendChild(a);
    }
}
