package com.bdqn.entity;

public class ListObject {

    public String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_score() {
        return _score;
    }

    public void set_score(String _score) {
        this._score = _score;
    }

    public com.bdqn.entity._source get_source() {
        return _source;
    }

    public void set_source(com.bdqn.entity._source _source) {
        this._source = _source;
    }

    public String _score;
    public _source _source;

}
