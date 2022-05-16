const dependencies = require('../package.json').dependencies;

const NOT_SHAREABLE_DEPENDENCIES = ['wdx'];

function getShareableDeps() {
    const shareableDependencies = {};
    for (const key of Object.keys(dependencies)) {
        if (!NOT_SHAREABLE_DEPENDENCIES.includes(key)) {
            shareableDependencies[key] = dependencies[key];
        }
    }

    return shareableDependencies;
}

module.exports = {
    getShareableDeps,
};
