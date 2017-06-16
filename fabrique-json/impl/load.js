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
                console.log( "> process: " + selection.query )

            /*
                var _ = require( 'underscore' );

                if( _.isFunction(then) ) {
                    then( output );
                }
             */

                selection.process( input, then, error );
        }
    };

};