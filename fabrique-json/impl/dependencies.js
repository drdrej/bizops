/**
 * Created by asiebert on 17.06.2017.
 */

module.exports = function( fnc ) {

    global.load = require('../impl/load');
    global.select = require( '../impl/select');

    // var _ = require( 'underscore' );

    fnc( );
};