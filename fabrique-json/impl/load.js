/**
 * Created by asiebert on 10.06.17.
 */
module.exports = function( input ) {

    return {
        process : function (
            selection,
            then,
            error
        ) {
            var hamsters = require('hamsters.js');

            hamsters.run(input, function( input ) {
                console.log( "> process: " + selection.query )
                return
                    selection.process( input );
            }, function( output ) {
                console.log( "> process-output: " );
                console.log( output );

                var _ = require( 'underscore' );

                if( _.isFunction(then) ) {
                    then( output );
                }
            }, 1, true );
        }
    };

};