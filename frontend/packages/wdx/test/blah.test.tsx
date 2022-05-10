import * as ReactDOM from 'react-dom';
import WButton from '../src/components/button';

describe('Button', () => {
  it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<WButton />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
});
