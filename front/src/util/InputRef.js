// Copyright (C) 2019 Alina Inc. All rights reserved.
import React from 'react';

class InputRef {
  constructor() {
    this.refs = [];
  }

  getInput() {
    const input = {};
    this.refs.forEach(({ ref, value }) => {
      const { current } = ref;
      const newValue = current.type === 'checkbox' ? current.checked : current.value;
      if (newValue !== value) {
        input[current.name || current.id] = newValue;
      }
    });
    return input;
  }

  getRef(value = '') {
    const ref = React.createRef();
    this.refs.push({ ref, value });
    return ref;
  }
}

export default InputRef;
