/**
 * Created by asiebert on 08.06.2017.
 */
// var expect = require( 'chai' ).expect;
var assert = require('chai').assert;

describe("select for jq", function() {
    describe("simple selection", function() {

        it("handle initialization.", function( done ) {
            var select = require( '../impl/select-jq' );

            assert.isNotNull(select, "some kind of object is created");
            assert.typeOf( select, "function" );

            done();
        });
    });

    describe("invalid selections", function() {
        var select = require( '../impl/select-jq' );

        it("can handle no param call", function() {
            var x = select();

            assert.typeOf( x, "array", "return value should be an array" );
            assert.lengthOf( x, 0, "return value should be empty" );
        });

        it("can handle param:what", function() {
            var what = {
                name: "testObj"
            };

            var x = select(what);

            assert.typeOf( x, "array", "return value should be an array" );
            assert.lengthOf( x, 1, "return value should have 1 object" );

            assert.include( x, what, "same same" );
        });
    });

});
