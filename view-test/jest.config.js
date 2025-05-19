module.exports = {
  moduleFileExtensions: [
    'js',
    'jsx',
    'json',
    'vue'
  ],
  transform: {
    '^.+\\.vue$': '@vue/vue2-jest',
    '^.+\\.(js|jsx)?$': 'babel-jest'
  },
  moduleNameMapper: {
    '^@/(.*)$': '<rootDir>/src/$1'
  },
  testMatch: [
    '**/tests/unit/**/*.spec.js'
  ],
  transformIgnorePatterns: ['/node_modules/'],
  testEnvironment: 'jsdom',
  testPathIgnorePatterns: ['/node_modules/'],
  verbose: true,
  roots: ['<rootDir>'],
  moduleDirectories: ['node_modules', 'src']
} 