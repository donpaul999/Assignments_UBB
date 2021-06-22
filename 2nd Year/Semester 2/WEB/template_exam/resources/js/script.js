function addAsset() {
    var name = $('#name').val();
    var description = $('#description').val();
    var value = $('#value').val();
    if(name.length >= 1 && description.length >= 1 && value > 0){
        $.post("/backend/add-asset.php", {name: name, description: description, value: value}).done(function(data){
            console.log($('#myTable tr').length);
            tableRow = "<tr>";
            if(value > 10)
                tableRow = "<tr style='color:red;'>";
            tableRow +=  "<th>" + $('#myTable tr').length + "</th>" +
                "<td>" + name + "</td>" +
                "<td>" + description + "</td>" +
                "<td>" + value + "</td>" +
                       "</tr>";
            $('#myTable tr:last').after(tableRow);
            $('#name').val('');
            $('#description').val('');
            $('#value').val('');
        });
    }
}
