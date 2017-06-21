/**
 * Created by asiebert on 21.06.2017.
 */

module.exports = function transfer( createFnc ) {
    var _ = require( 'underscore' );

    if( !_.isFunction( createFnc ) )  {
        console.error( "Is not a function! " );
        console.error( createFnc );

        return this;
    }

    this.factory = createFnc;

};