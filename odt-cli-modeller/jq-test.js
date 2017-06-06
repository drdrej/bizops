const jq = require('node-jq');

const filter = '.Invoices[1:100000000]'; // immer maximale >=länge angeben!
const jsonPath = '../odt-cli-core/data.json';
const options = {
    output: 'json'
};

const output = "../odt-cli-core/odt-model.json";

function handleEntry( entry ) {
    console.log( "Entry: " + entry );
    var moment = require( 'moment' );

    // "2010-10-20 4:30 +0000"
    var dateStr = entry[0][0];     // März 2017
    var d = moment(dateStr, "MMM YYYY" , 'de' );

    console.log( dateStr + " is valid: " + d.isValid() );
};

var result = [];
jq.run(filter, jsonPath, options)
    .then(
        function(output) {
            console.log(output);
            result.push( output );

            handleEntry( output );
})
.catch(
    function(err)  {
        console.error(err);
});

console.log( "TODO: save output!" );