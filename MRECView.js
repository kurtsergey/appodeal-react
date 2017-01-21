import { PropTypes, Component } from 'react';
import { requireNativeComponent, View, DeviceEventEmitter } from 'react-native';

/*var iface = {
  name: 'MrecView',
  propTypes: {
	fix: PropTypes.string,
    ...View.propTypes // include the default view properties
  },
};*/

class iface extends Component{
	constructor(props) {
		super(props);
		DeviceEventEmitter.addListener('onMrecShown', (e) => this.forceUpdate());
	}
	render() {
		return <MrecView {...this.props} />;
	}
}

iface.propTypes = {
	fix: PropTypes.string,
}

var MRECView = requireNativeComponent('MrecView', iface);

export default MRECView;