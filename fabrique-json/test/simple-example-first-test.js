/**
 * Created by asiebert on 08.06.2017.
 */

var assert = require('chai').assert;

var deps = require('../impl/dependencies');


describe("Process examples", function() {

        describe("process - example 1", function () {

            it("handle initialization.",
                function (done) {
                deps( function( ) {

                    var input = {
                        "Invoices" : [
                            {
                              value : 10
                            },
                            {
                                value : 22
                            }
                        ],
                        name: "Test"
                    };

                    load(input)
                        .process(
                            select(".Invoices").first(),
                            function ( result ) {
                               console.log("nodes selected: ");
                               console.log( result );

                               assert.typeOf( result, "object", "return value should be an object" );
                               // assert.lengthOf( result, 0, "return value should be empty" );

                               done();
                        }
                    );
                })
            });


        });

    });



