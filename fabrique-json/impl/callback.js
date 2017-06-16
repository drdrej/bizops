/**
 * Created by asiebert on 16.06.2017.
 */

module.exports = function( then ) {
    var _ = require( 'underscore' );

    if( _.isFunction(then) ) {
        return {
            exec : function ( params ) {
                then( params );
            }
        };
    } else {
        return {
            exec: function() {}
        }; // noop
    }
};