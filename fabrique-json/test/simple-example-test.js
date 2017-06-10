/**
 * Created by asiebert on 08.06.2017.
 */
var assert = require('chai').assert;

describe("Process examples", function() {

        describe("process - example 1", function () {

            it("handle initialization.", function (done) {
                var load = require('../impl/load');

                var input = {
                    name: "Test"
                };

                load(input).process(
                    each(".Invoices"),
                    function (nodes) {
                        console.log("nodes selected: ");
                        console.log(nodes);

                        done();
                    }
                );

            });
        });

    });



/*.
 create({
 value: " Hello my nam is {{name}}."
 })

 .
 dump().
 then().
 error()*/