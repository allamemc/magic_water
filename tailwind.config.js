module.exports = {
  mode: process.env.NODE_ENV ? 'jit' : undefined,
  purge: ["./src/**/*.html", "./src/**/*.js"],
  theme: {
    extend: {},
  },
  variants: {
    extend: {},
  },
  plugins: [require("daisyui")],
    daisyui: {

        themes: true,

    },
}