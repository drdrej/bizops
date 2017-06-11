module.exports =
    function(
        input
) {
    var moment = require( "moment" );

    console.log( "date: " + input );

    return moment( input, "DD.mm.YYYY" );
};