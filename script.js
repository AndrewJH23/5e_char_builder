document.addEventListener('DOMContentLoaded', (event) => {
  document.getElementById('myForm').addEventListener('submit', function(e){
    e.preventDefault();
    var selectedValue1 = this.elements["selection1"].value;
    var selectedValue2 = this.elements["selection2"].value;
    document.getElementById('display').innerHTML = 'You selected: ' + selectedValue1;
    document.getElementById('display2').innerHTML = 'You selected: ' + selectedValue2;
  });

  document.getElementById('download-button').addEventListener('click', function(e){
    e.preventDefault();
    var selectedValue1 = document.getElementById("selection1").value;
    var selectedValue2 = document.getElementById("selection2").value;
    var All = selectedValue1 + "\n" + selectedValue2
    var blob = new Blob([All], {type: "text/plain;charset=utf-8"});
    var url = URL.createObjectURL(blob);
    var link = document.createElement('a');
    link.href = url;
    link.download = 'selectedOption.txt';
    link.click();
  });
});

document.getElementById('fileUpload').addEventListener('change', function(event) {
    var file = event.target.files[0];
    var reader = new FileReader();

    reader.onload = function(event) {
        document.getElementById('content').textContent = event.target.result;
    };

    reader.onerror = function(event) {
        console.error('File could not be read: ' + event.target.error);
    };

    reader.readAsText(file);
});
