// Searches for a given search term.  If there are no results,
// navigates to no-results.html
function search(term) {
    window.location.href = "no-results.html";
}

function simpleAnswers() {
    var data = [
        [1, 'How do I open my photo galery?', 'Android', 'Vin Deisel'],
        [2, 'How do I open my camera?', 'Iphone', 'Michael Jackson']
    ];
    console.log(data); 
    var temp, item, a, i;
    temp = document.getElementById('simpleAnswers');
    question = temp.content.querySelector('h1'); 
    device = temp.content.querySelector('p'); 
    for (i = 0; i < data.length; i++) {
        a = document.importNode(question, true);
        a.textContent += 'Question: ' + data[i][1];
        document.body.appendChild(a);

        a = document.importNode(device, true); 
        a.textContent += 'Device: ' + data[i][2]; 
        document.body.appendChild(a);
    }
}
